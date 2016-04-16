#!/usr/bin/env python3
# -*- coding: utf-8 -*-

#fetch Gravatars

import os
import requests
import subprocess
import hashlib


def md5_hex(text):
    m = hashlib.md5()
    m.update(text.encode('ascii', errors='ignore'))
    return m.hexdigest()

size = 90
output_dir = os.path.join('.git', 'avatar')

os.makedirs(output_dir, exist_ok=True)

gitlog = subprocess.check_output(['git', 'log', '--pretty=format:%ae|%an'])
authors = set(gitlog.decode('ascii', errors='ignore').splitlines())
print(authors)
for author in authors:
    email, name = author.split('|')
    output_file = os.path.join(output_dir, name + '.png')
    if not os.path.exists(output_file):
        grav_url = "http://www.gravatar.com/avatar/" + md5_hex(email) + "?d=identicon&s=" + str(size)
        print(email, name, grav_url)
        r = requests.get(grav_url)
        if r.ok:
            with open(output_file, 'wb') as img:
                img.write(r.content)