# -*- coding: utf-8 -*-

# Bibliothèques pour l'API REST
from flask import Flask
from markupsafe import escape
from flask_jwt import JWT, jwt_required, current_identity
from flask import jsonify
from flask_jwt_extended import *
from flask import request

# Bibliothèque pour le hash des mots de passe
import hashlib

# Bibliothèques pour la traduction
from googletrans import Translator as GoogleTranslator
from translate import Translator as MyMemoryTranslator
from word2word import Word2word

# Bibliothèques diverses (utils)
from werkzeug.security import safe_str_cmp
import pyodbc
import pandas as pd
import json

languages = {
    'Français' : 'fr',
    'Allemand' : 'de',
    'Anglais'  : 'en',
    'Espagnol' : 'es'
    }


app = Flask(__name__)

app.config['SECRET_KEY'] = 'secretkey'

conn = pyodbc.connect('Driver={SQL Server};'
                      'Server=DESKTOP-DK7I0AP;'
                      'Database=YOURTRAD;'
                      'Trusted_Connection=yes;')

cursor = conn.cursor()

#jwt = JWT(app, login, identity)
jwt = JWTManager(app)


@app.route('/')
def index():
    return 'index'


@app.route('/user', methods=['POST', 'GET'])
def user():
    if request.method == 'POST':
        data = json.loads(request.form['data'])
        
        sql = """SELECT * FROM [USER]
                 WHERE EMAIL = '""" + data['email'] + """'"""
        
        print(sql)
        
        cursor.execute(sql)
        res = cursor.fetchall()
        conn.commit()
        
        if len(res) != 0:
            return jsonify(response='Cette adresse email est déjà utilisée.')
        
        if len(data['password']) < 5:
            return jsonify(response='Votre mot de passe est trop court.')
        
        # Hash du mot de passe pour la sécurité
        data['password'] = hashlib.sha256(data['password'].encode()).hexdigest()
        
        sql = """INSERT INTO [USER] (firstname, lastname, email, password) 
            VALUES ('{firstname}', '{lastname}', '{email}', '{password}')"""
           
        query = sql.format(**data)
        cursor.execute(query)
        conn.commit()
        
        return jsonify(response='Vous êtes bien enregistré.')
    
    return jsonify(response='Erreur inconnue.')

@app.route('/login', methods=['POST'])
def login():
    
    token = ""
    
    if request.method == 'POST':
    
        data = json.loads(request.form['data'])
        
        
        sql = """SELECT * FROM [USER]
         WHERE EMAIL = '""" + data['email'] + """'"""
        
        print(sql)
        
        cursor.execute(sql)
        res = cursor.fetchall()
        conn.commit()
        
        if len(res) == 0:
            return jsonify(response='Adresse email inconnue.', token=token)
        
        password_input = hashlib.sha256(data['password'].encode()).hexdigest()
        
        # Hash du mot de passe pour la sécurité
        data['password'] = hashlib.sha256(data['password'].encode()).hexdigest()
        
        sql = """SELECT PASSWORD FROM [USER]
                 WHERE EMAIL = '""" + data['email'] + """'"""
        
        print(sql)
        
        cursor.execute(sql)
        password_bdd = cursor.fetchall()[0][0]
        conn.commit()
        
        print(password_input, password_bdd)
        
        if user and safe_str_cmp(password_input.encode('utf-8'), password_bdd.encode('utf-8')):
            token = create_access_token(identity = data['email'])
            #print(token)
            return jsonify(response='Vous êtes connecté.', token=token)
        else:
            return jsonify(response='Mauvais mot de passe.', token=token)
    
    return jsonify(response='Erreur inconnue.', token=token)


@app.route('/translate_sentence', methods=['POST'])
def translate_sentence():
    if request.method == 'POST':
        data = json.loads(request.form['data'])
        
        text  = data['translate_input']
        src   = languages[data['language_input']]
        dest  = languages[data['language_output']]
        
        if data['chosen_model'] == 'Google Translate':
            translator = GoogleTranslator()
            res = translator.translate(text, src=src, dest=dest)
            return jsonify(translation=res.text)
        
        elif data['chosen_model'] == 'My Memory':
            translator  = MyMemoryTranslator(provider='mymemory',from_lang=src,to_lang=dest)
            res = translator.translate(text)
            return jsonify(translation=res)

def identity(payload):
    user_id = payload['identity']
    return userid_table.get(user_id, None)


if __name__ == '__main__':
    app.run()