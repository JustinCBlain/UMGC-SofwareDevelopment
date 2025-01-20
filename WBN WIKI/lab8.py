"""
Name: Blain, Justin
Class: SDEV 300
Date: 05.03.24

Goal: Login
"""
# Import
import sys
import string
from datetime import datetime
# from passlib.hash import sha256_crypt
from flask import Flask, render_template, flash, redirect, url_for, request
import pandas as pd

# Ingest all data
try:
    df = pd.read_csv("char_info.csv")
    df = df.fillna("None")
    df.columns = df.columns.str.casefold()
    df.set_index('character', inplace=True)
    df = df.T
except OSError:
    print("Data error: Unable to read files!")
    sys.exit()

users = {
    "Lou": "Wilson",
    "Brennan": "LeeMulligan",
    "Abria": "Iyangar",
    "Erika": "Ishii"
}
user_list = []
LOGGED_IN = False

# Init
app = Flask(__name__, static_url_path='/static')
app.config.update(SECRET_KEY='1234')


def create_app():
    app = Flask(__name__)
    with app.app_context():
        display()
    return app


@app.route('/')
def home():
    """For now, a simple refreshable clock"""
    time = datetime.now().strftime('%H:%M:%S')
    return f"At the refresh the time will be: {time}"

@app.route('/xss', methods=['GET', 'POST'])
def index():
    if request.method == 'POST':
        msg = request.form['msg']
        flash(msg, "error")
    return(render_template('xss.html'))


@app.route('/login', methods=['GET', 'POST'])
def login():
    """Checks against users table to allow or deny access"""
    title = "Login"
    global LOGGED_IN
    if LOGGED_IN:
        flash("But you're already logged in!", "error")
        return redirect(url_for('Suvi'))
    if request.method == 'POST':
        username = request.form['username']
        pw = request.form['pass']
        with open("Logger.txt", 'a', encoding="utf-8") as logger:
            logger.write(f"Username: {username}\n"
                         f"Password: {pw}\n"
                         f"Time: {datetime.now()}\n"
                         f"IP: {request.remote_addr}\n\n")
            if username in users.keys():
                if pw == users[username]:
                    logger.write("**Success**\n")
                    LOGGED_IN = True
                    return redirect('Suvi')
                logger.write("**Failure**\n")
                flash('Looks like you botched your memory check.\n'
                      'Please summon a new password',
                      'error')
                return redirect(url_for('register'))
            flash('Looks like you\'re not in the party yet.\n'
                  'Please step over here.',
                  'error')
            return redirect(url_for('register'))
    return render_template('form.html', title=title)


@app.route('/register', methods=['GET', 'POST'])
def register():
    """Creates a new user or update the password of an old one"""
    title = "Register/Update"
    global users
    if LOGGED_IN:
        flash("But you're already logged in!", "error")
        return redirect(url_for('Suvi'))
    if request.method == 'POST':
        username = request.form['username']
        pw = request.form['pass']
        with open("CommonPassword.txt", 'r', encoding="utf-8") as pwds:
            for line in pwds:
                if len(pw) < 12:
                    flash("Don't be so basic.", "error")
                    return redirect(url_for('register'))
        if len(pw) < 12:
            flash("Look's like you failed your length check."
                  "\nWe only accept 12 and over. ;)", "error")
            return redirect(url_for('register'))
        if not set(string.ascii_lowercase) & set(pw):
            flash("Gonna need to see some halflings in there.", "error")
            return redirect(url_for('register'))
        if not set(string.ascii_uppercase) & set(pw):
            flash("Gonna need to see some giants in there.", "error")
            return redirect(url_for('register'))
        if not set(string.digits) & set(pw):
            flash("Gonna need to see some constructs in there.", "error")
            return redirect(url_for('register'))
        if not set(string.punctuation) & set(pw):
            flash("Gonna need to see some aberations in there.", "error")
            return redirect(url_for('register'))
        if username in users.keys():
            flash('Your password familiar has been reincarnated.', 'error')
        else:
            flash("This looks like a great character. Come on in!", "error")
        users[username] = pw
        return redirect(url_for('login'))
    return render_template('form.html', title=title)


@app.route('/<char>')
def display(char):
    """Displays wiki pages"""
    global LOGGED_IN
    if not LOGGED_IN:
        flash("Looks like you botched you stealth check.", "error")
        return redirect('/login')
    if request.method == 'POST':
        LOGGED_IN = False
        return redirect(url_for('login'))
    char_df = df[char]
    origin = char_df.get('origin')
    summary = char_df.get('summary')
    items = char_df.get('items').split(',')
    abilities = char_df.get('abilities')
    cantrips = char_df.get('cantrips').split(',')
    spells = char_df.get('spells').split(',')
    trivia = char_df.get('trivia').splitlines()
    pic1 = char_df.get('pic1')
    pic2 = char_df.get('pic2')

    return render_template('character.html',
                           character=char,
                           origin=origin,
                           summary=summary,
                           items=items,
                           abilities=abilities,
                           cantrips=cantrips,
                           spells=spells,
                           trivia=trivia,
                           pic1=pic1,
                           pic2=pic2)


@app.route('/logout')
def logout():
    """What is says on the tin XD"""
    global LOGGED_IN
    LOGGED_IN = False
    return redirect('/login')
