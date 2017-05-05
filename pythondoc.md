$python --version
  Python 3.5.1

$python -m pip install --upgrade pip

$pip freeze|grep 'numpy'

$pip install --user numpy scipy matplotlib ipython jupyter pandas sympy nose

$python
>>>import numpy
>>>numpy.version.version

#### python: string, list, dict
python list similar to strings
colors = ['red', 'blue', 'green']
  print colors[0]    ## red
  print colors[2]    ## green
  print len(colors)  ## 3

b = colors   ## Does not copy the list but two variable pointing to same list

squares = [1, 4, 9, 16]
  sum = 0
  for num in squares:
    sum += num
  print sum  ## 30

list = ['larry', 'curly', 'moe']
  if 'curly' in list:
    print 'yay'

> print the numbers from 0 through 99
  for i in range(100):
    print i

* Access every 3rd element in a list
  i = 0
  while i < len(a):
    print a[i]
    i = i + 3
### methods on list ex: list.append, sort, pop, reverse, remove, index, extend  ##Note the list itself is modified with these methods

* functions of list
a = [5, 1, 4, 3]
  print sorted(a)  ## [1, 3, 4, 5]
  print a  ## [5, 1, 4, 3]
  print len(a) ##4

strs = ['aa', 'BB', 'zz', 'CC']
  print sorted(strs)  ## ['BB', 'CCCC', 'aaa', 'zz'] (case sensitive)
  print sorted(strs, reverse=True)   ## ['zz', 'aa', 'CC', 'BB']
  print sorted(strs, key=len) 

* use of custom function
  strs = ['xc', 'zb', 'yd' ,'wa']

  def MyFn(s):
    return s[-1]

  print sorted(strs, key=MyFn)  ## ['wa', 'zb', 'xc', 'yd']

* tuple is a fixed size grouping of elements, such as an (x, y) like "struct". A function that needs to return multiple values can just return a tuple of the values.
Methods on tuple are similar to one on list except element cannot be changed

tuple = (1, 2, 'hi')
  print len(tuple)  ## 3
  print tuple[2]    ## hi

tuple = ('hi',)   ## size-1 tuple needs to be followed by comma

(x, y, z) = (42, 13, "hike")
  print z  ## hike

  (err_string, err_code) = Foo()  ##assigns a tuple to return values from funcion Foo

* dict key value hash table

dict = {key1:value1, key2:value2}

dict = {}
  dict['a'] = 'alpha'
  dict['g'] = 'gamma'

print dict
print dict['a'] 
if 'z' in dict: print dict['z'] 
for key in dict: print key
for key in dict.keys(): print key
print dict.values()
for key in sorted(dict.keys()):
    print key, dict[key]
print dict.items()  ##  [('a', 'alpha'), ('o', 'omega')]

## use of % with dict
hash = {}
  hash['word'] = 'garfield'
  hash['count'] = 42
  s = 'I want %(count)d copies of %(word)s' % hash  # %d for int, %s for string
  # 'I want 42 copies of garfield'

* del deletes a var, list, element of a list, dict, or element of a dict
var = 6
  del var  # var no more!
  
  list = ['a', 'b', 'c', 'd']
  del list[0]     ## Delete first element
  del list[-2:]   ## Delete last two elements
  print list      ## ['b']

  dict = {'a':1, 'b':2, 'c':3}
  del dict['b']   ## Delete 'b' entry
  print dict      ## {'a':1, 'c':3}

* file
f = open('foo.txt', 'rU')  #opens a file with Universal line ending and keeping them as \n
for line in f:   ## iterates over the lines of the file
    print line,    ## trailing , so print does not add an end-of-line char
                   ## since 'line' already includes the end-of line.
  f.close()

Note: above way of reading a line at a time doesn't require the whole file to be in memory but just a line
f.readlines() reads the whole file into memory and returns its contents as a list of its lines.
f.read() method reads the whole file into a single string

f.write(string) 

import codecs
f = codecs.open('foo.txt', 'rU', 'utf-8')

sys.exit(0)  Note: to exit from program


#### Natural Language Processing made easy using SpaCy in python (older or other lib NLTK, CoreNLP)
NLP for text classification. entity detection, machine translation and concept translation.

Cython is a C extension of Python for C like performance
sudo pip3 install spacy

Note: There are different type of models provided in the package which contains the information about language – vocabularies, trained vectors, syntaxes and entities.

import spacy 
nlp = spacy.load(“en”)

document = unicode(open(filename).read().decode('utf8')) 
document = nlp(document)
dir(document)   ## outputs [ 'doc', 'ents', … 'mem']

Note: Tokenization, part of speech tagging 















