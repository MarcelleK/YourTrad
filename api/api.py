# -*- coding: utf-8 -*-

from flask import Flask
from markupsafe import escape
from flask_jwt import JWT, jwt_required, current_identity
from flask import request

import pyodbc
import pandas as pd

"""
class User(object):
    def __init__(self, id, username, password):
        self.id = id
        self.username = username
        self.password = password

    def __str__(self):
        return "User(id='%s')" % self.id
    

def authenticate(username, password):
    user = username_table.get(username, None)
    if user and safe_str_cmp(user.password.encode('utf-8'), password.encode('utf-8')):
        return user

def identity(payload):
    user_id = payload['identity']
    return userid_table.get(user_id, None)

"""

app = Flask(__name__)

"""
conn = pyodbc.connect('Driver={SQL Server};'
                      'Server=DESKTOP-DK7I0AP;'
                      'Database=YOURTRAD;'
                      'Trusted_Connection=yes;')

df = pd.read_sql_query('SELECT TOP 10 * FROM USER',conn)
print(df.to_json())
print(type(df))
"""

@app.route('/')
def index():
    return 'Index Page'


@app.route('/user', methods=['POST', 'GET'])
def user():
    error = None
    if request.method == 'POST':
        print(request.form)
        #if valid_login(request.form['username'],
        #               request.form['password']):
        #    return log_the_user_in(request.form['username'])
        #else:
        #    error = 'Invalid username/password'
            
    return 'ok'


"""
@app.route('/hello')
def hello():
    return 'Hello, World'

@app.route('/user/<username>')
def show_user_profile(username):
    # show the user profile for that user
    return 'User %s' % escape(username)

@app.route('/post/<int:post_id>')
def show_post(post_id):
    # show the post with the given id, the id is an integer
    return 'Post %d' % post_id

@app.route('/path/<path:subpath>')
def show_subpath(subpath):
    # show the subpath after /path/
    return 'Subpath %s' % escape(subpath)

@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        return do_the_login()
    else:
        return show_the_login_form()
    
@app.route("/users")
def users_api():
    users = get_all_users()
    return jsonify([user.to_json() for user in users])
"""