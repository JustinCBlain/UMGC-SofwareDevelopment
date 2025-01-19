"""
Name: Blain, Justin
Class: SDEV 300
Date: 05.03.24

Goal: Login
"""
# Import
import sys
from datetime import datetime
# from passlib.hash import sha256_crypt

from flask import Flask, render_template, flash, redirect, url_for, request
import pandas as pd

app = Flask(__name__, static_url_path='/static')
app.config.update(
    SECRET_KEY='1234')
try:
    df = pd.read_csv("char_info.csv")
    df = df.fillna("None")
    df.columns = df.columns.str.casefold()
    df.set_index('character', inplace=True)
    df = df.T

except OSError:
    print("Data error: Unable to read file!")
    sys.exit()

users = {
    "Lou": "Wilson",
    "Brennan": "LeeMulligan",
    "Abria": "Iyangar",
    "Erika": "Ishii"
}


# Was getting an out-of-scope error
def create_app():
    app = Flask(__name__)
    with app.app_context():
        display()
    return app


logged_in = False


@app.route('/')
def home():
    time = datetime.now().strftime('%H:%M:%S')
    return f"At the refresh the time will be: {time}"


@app.route('/login', methods=['GET', 'POST'])
def login():
    title = "Login"
    if logged_in:
        flash("But you're already logged in!", "error")
        return redirect(url_for('display(Suvi)'))
    elif request.method == 'POST':
        username = request.form['username']
        pw = request.form['pass']
        if username in users.keys():
            if pw == users[username]:
                return redirect('Suvi')
            else:
                flash('Looks like you botched your memory check. Try again.',
                      'error')
                return redirect(url_for('login'))
        else:
            flash('Looks like you\'re not in the party yet. '
                  'Please step over here.',
                  'error')
            return redirect(url_for('register'))
    return render_template('form.html', title=title)


@app.route('/register')
def register():
    title = "Register"
    if logged_in:
        flash("But you're already logged in!", "error")
        return redirect(url_for('display(Suvi)'))
    elif request.method == 'POST':
        username = request.form['username']
        pw = request.form['pass']
        if username in users.keys():
            flash('Hey, Don\'t I know you?', 'error')
            return redirect(url_for('login'))
        else:
            users[username] = pw
            return redirect(url_for('login'))
    return render_template('form.html', title=title)


# Three char pages
@app.route('/<char>')
def display(char):
    if not logged_in:
        flash("Looks like you botched you stealth check.", "error")
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

    return render_template('home.html',
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
