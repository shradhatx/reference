#### install flask on mac
>below steps not used
$pip list
$pip search Flask
$pip install virtualenv

$virtualenv flask_virtual
$source flask_virtual/bin/activate
$flask_virtual/bin/easy_install flask

$pip freeze --local >requirements.txt
$deactivate
$virtualenv -p /usr/bin/python2.7 py27_env
$source py27_env/bin/activate
$pip install -r requirements.txt
> upto here

>working test
$conda create -n python3 python=3.5.1 anaconda
$source activate python3
$python --version
$conda search Flask

$python
>>>import flask
>>>exit()

$vi flask_app.py
from flask import Flask
app = Flask("First App")
@app.route('/')
def index():
 return"Yes it is working"

if __name__ =="__main__":
  app.run()


$python flask_app.py

Check url localhost:5000






#### using conda
conda list
conda search Flask
python --version




