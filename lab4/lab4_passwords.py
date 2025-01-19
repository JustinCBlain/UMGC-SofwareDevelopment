"""
Name: Blain, Justin
Class: SDEV 300
Date: 04.02.24

Goal: Password Hashification
"""

# Import
import hashlib
import string
import secrets


def password(pwd_length, pwd_strength):
    """
    Password generator

    Generates password of length = pwd_length.
    pwd_strength 1 = lowercase only
    pwd_strength 2 = lowercase and uppercase
    pwd_strength 3 = lowercase, uppercase, and numbers
    pwd_strength 4 = lowercase, uppercase, numbers, and special characters
    Final password verified to contain one of each character type before passed
    """
    characters = string.ascii_lowercase
    if pwd_strength >= 2:
        characters = characters + string.ascii_uppercase
    if pwd_strength >= 3:
        characters = characters + string.digits
    if pwd_strength >= 4:
        characters = characters + string.punctuation
    security = 0
    while security < pwd_strength:
        pwd = "".join(secrets.choice(characters) for i in range(pwd_length))
        security = 0
        if set(string.ascii_lowercase) & set(pwd):
            security += 1
        if set(string.ascii_uppercase) & set(pwd):
            security += 1
        if set(string.digits) & set(pwd):
            security += 1
        if set(string.punctuation) & set(pwd):
            security += 1
    return pwd


def saltify (pwds):
    salty_pwds = []
    for pwd in pwds:
        salt = password(4,4)
        salty_pwd = pwd + salt
        salty_pwds.append(salty_pwd)
    return salty_pwds


def encode_all(pwds):
    for pwd in pwds:
        pwd = pwd.encode()
        print(f"{hashlib.md5(pwd).hexdigest()},"
              f"{hashlib.sha256(pwd).hexdigest()},"
              f"{hashlib.sha512(pwd).hexdigest()},"
              f"{hashlib.sha3_256(pwd).hexdigest()},"
              f"{hashlib.sha3_512(pwd).hexdigest()},"
              f"{hashlib.blake2s(pwd).hexdigest()},"
              f"{hashlib.blake2b(pwd).hexdigest()}")


pwds = [
    "pwd", "password", "passwordpassword",
    "Pwd!", "Pass1234", "PassW0rd1234!@#$",
    "r@T3", "1StR@t3!", "1$tFiR2tR@t3!r8*",
    "5hM%", "v5X~}JPP", ";N7LF@qvKK>`-i{B"]

print(pwds)
print(saltify(pwds))
print(encode_all(pwds))
print(encode_all(saltify(pwds)))
