# git - reference material
On mac you need brew- to install
/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"

> Check out https://hub.github.com/  a command line wrapper for git
$brew install hub
git version
hub version

sudo git init
sudo git add .
sudo git commit -m "initial live site commit"

-- create new repository from CLI
curl -u 'USER' https://api.github.com/user/repos -d '{"name":"REPO","private":"true"}'
# Remember replace USER with your username and REPO with your repository/application name!
git remote add origin git@github.com:USER/REPO.git
git push origin master

> Or go to github.com
create new proj

cd ~
git clone <url>
git remote -v

vi README.md
git status
git add <fn>
or
git add .
git commit -m “comments”
git status
git log
git push origin master

Blog: http://www.dataschool.io
Newsletter: http://www.dataschool.io/subscribe/
Twitter: https://twitter.com/justmarkham
GitHub: https://github.com/justmarkham

Pro Git: http://git-scm.com/book
GitHub Help: https://help.github.com/
GitRef.org: http://gitref.org/
Git quick reference for beginners: http://www.dataschool.io/git-quick-re...
GitHub Guides: https://guides.github.com/

fork makes a copy of repo to your local computer

To update fetch & then merge & make changes and then push.
Then ask the keeper to pull the changes when done.


$git remote add upstream <url>
$git remote -v
$git fetch upstream
note: the above command creates a dir upstream/master
$git merge upstream/master
$git push origin master



### Technical reference
* [elkref] - all about elastic stack
* [sparkref] - Spark
* [nosqlref] - Cassandra, HBase, MongoDB
* [logstashref] - Logstash

   [elkref]: <https://github.com/shradhatx/reference/elkdoc>
   [sparkref]: <https://github.com/shradhatx/reference/elkdoc>
   [nosqlref]: <https://github.com/shradhatx/reference/nosqldoc>
   [logstashref]: <https://github.com/shradhatx/reference/logstashdoc>

















### Technical reference
* [elkref] - all about elastic stack
* [sparkref] - Spark
* [nosqlref] - Cassandra, HBase, MongoDB
* [logstashref] - Logstash

   [elkref]: <https://github.com/shradhatx/reference/elkdoc>
   [sparkref]: <https://github.com/shradhatx/reference/elkdoc>
   [nosqlref]: <https://github.com/shradhatx/reference/nosqldoc>
   [logstashref]: <https://github.com/shradhatx/reference/logstashdoc>


#### git on EC2
create rsa key and keep public key in ~/.ssh/authorized_keys

create .ssh dir
vi ~/.ssh/authorized_keys

install git
cd sitesrepo.git websites 
cd sitesrepo.git && git init --bare

mkdir hooks
vi post-receive
#!/bin/sh
GIT_WORK_TREE=/home/ubuntu/websites git checkout -f

chmod +x post-receive












