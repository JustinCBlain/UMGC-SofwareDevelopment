"""
Name: Blain, Justin
Class: SDEV 325
Date: 6.13.24

Slightly less Simple Vulnerability Demo
"""

# Import
import os
from datetime import datetime, timedelta
import hashlib

import pandas as pd
from flask import Flask, redirect, request, flash, render_template

# Init
app = Flask(__name__)
app.config.update(SECRET_KEY='1234')

users = {
    "Lou": "Wilson",
    "Brennan": "LeeMulligan",
    "Abria": "Iyangar",
    "Erika": "Ishii",
    "admin": "admin"
}

for user, pwd in users.items():
    pwd = pwd + ";N7LF@qvKK>`-i{B"
    pwd = pwd.encode()
    pwd = hashlib.sha256(pwd).hexdigest()
    users.update({user: pwd})


LOGGED_IN = ""

def create_app():
    app = Flask(__name__)
    with app.app_context():
        display()
    return app


# Functions
@app.route('/calc', methods=['GET', 'POST'])
def calc():
    """Calculation Printer"""
    if request.method == 'POST':
        msg = request.form['equation']
        flash(f"{eval(msg)}", 'error')
    fields = ["equation"]
    return render_template("home.html", user=LOGGED_IN, fields=fields)


@app.route('/browse', methods=['GET', 'POST'])
def browse():
    """File Browser"""
    global LOGGED_IN
    if not LOGGED_IN:
        flash("IMPOSTER!", "error")
        return redirect('/login')
    if request.method == 'POST':
        user = request.form['user']
        if user == 'admin':
            flash(f"{os.listdir()}")
        elif user in os.listdir("users"):
            path = f"users/{user}"
            flash(f"{os.listdir(path)}")
        else:
            flash("No, Thank You.", 'error')
    return render_template("home.html", user=LOGGED_IN)


@app.route('/permissions', methods=['GET', 'POST'])
def permissions():
    """Permissions Modifier"""
    if LOGGED_IN != "admin":
        flash("IMPOSTER!", "error")
        return redirect('/login')
    if request.method == 'POST':
        user = request.form['user']
        demotion = abs(int(request.form['demotion']))
        old_level = 5
        flash(f"{user}'s old access level: {old_level}", 'error')
        new_level = old_level - demotion
        flash(f"{user}'s new access level: {new_level}", 'error')
    return render_template("home.html", user=LOGGED_IN)


@app.route('/login', methods=['GET', 'POST'])
def login():
    """LOGOUT/LOGIN"""
    global LOGGED_IN
    LOGGED_IN = ""
    if request.method == 'POST':
        time = datetime.now()
        ip = request.remote_addr
        username = request.form['user']
        pw = request.form['pass']
        pw = pw + ";N7LF@qvKK>`-i{B"
        pw = pw.encode()
        pw = hashlib.sha256(pw).hexdigest()
        if not guardian(ip):
            return redirect('/login')
        if username in users.keys():
            if pw == users[username]:
                log(time, ip, True, username, pw)
                LOGGED_IN = username
                return redirect('/browse')
            log(time, ip, False, username, pw)
            flash('Nope.', 'error')
            return redirect('/login')
    return render_template('home.html')


def log (time, ip, success, username, pw):
    with open("Logger.csv", 'a', encoding="utf-8") as logger:
        log = [time, ip, success, username, pw]
        df = pd.DataFrame([log])
        df.to_csv(logger, header=False)


def guardian (ip):
    """Login Moderation"""
    df = pd.read_csv("Logger.csv")
    df = df.loc[df['IP'] == ip]
    time_limit = timedelta(seconds=60)
    df = df.loc[pd.to_datetime(df['Time']) >= datetime.now() - time_limit]
    time_out = timedelta(seconds=0)

    if len(df.index) < 5:
        return True
    time_out = timedelta(seconds=60)
    time_in = pd.to_datetime(df.iat[-1, 1]) + time_out
    flash(f'You\'re in timeout until {time_in:%H:%M:%S}', 'error')
    if datetime.now() >= time_in:
        flash(f'You\'re still in timeout until {time_in:%H:%M:%S}', 'error')
        return False
