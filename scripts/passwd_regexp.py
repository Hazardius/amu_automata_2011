# -*- coding: utf-8 -*-
import sys
import re
import time


try:
  #spróbuj pobrać nazwę pliku
  filename = sys.argv[1]
except:
  # nie podano nazwy pliku
  filename = None
  
if filename is not None:
  try:
    file = open(filename,'r') #otwieramy plik
  except:
    print("Błąd podczas otwierania pliku.")
    
  if file:
    pattern = "^.*:(\w+[^a])\s(\w+):\/home\/students\/.*$"
    unique_results = []
    for line in file: #iterujemy linijka po linijce
      result = re.match(pattern, line)
      if result:
	if result.groups()[0] not in unique_results:
	  unique_results.append(result.groups()[0])
    file.close()
    
    print("Zakończono przeszukiwanie pliku.")
    for i in range(0, 3):
      time.sleep(1)
      print(".")
    
    for name in unique_results:
      time.sleep(0.1)
      print(name)
    print("Wyniki: %d") % (len(unique_results))

else:
  print('#####################################################')
  print('# Autor: Łukasz Zarzecki                             ')
  print('# Skrypt wyciągający imiona z passwd                 ')
  print('#                                                    ')
  print('# Usage: python passwd_regexp.py <nazwa_pliku_passwd>')
  print('#####################################################')