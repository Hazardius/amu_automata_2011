#!/usr/bin/python
# -*- coding: utf-8 -*-
"""Zadanie passwd4"""
import sys
import re
import os
import subprocess
import tempfile
import time
import platform


"""Analiza pliku passwd, gdzie uzywamy polecen powlokowych
   W przypadku nie podania pliku passwd, plik zostanie pobrany
"""

#Sprawdzamy, jaka platforma
if platform.system() != 'Linux':
    print "Prosze uruchomic skrypt na Linuxie"
    time.sleep(2)
    sys.exit()

"""Zdefiniowanie polecenia powloki do analizy"""
def shell(plik):
    """Funkcja systemowa, do ktorej trafia wynik programu"""
    subprocess.Popen('sort "%s" | uniq -c | sort -k 1rn ' %plik, shell=True)

"""Jezeli plik nie zostal podany, pobieramy z internetu"""     
def passwd(plik):
    """Pobieranie pliku passwd"""
    subprocess.Popen(
    'wget -q -O %s http://math.uni.lodz.pl/~polrola/strony/0708l-systop_TM/passwd'
    %plik, shell=True)

if len(sys.argv) < 2:
    print ("Nie podano pliku passwd")
    print ("Pobieranie pliku passwd ...")
    P = tempfile.NamedTemporaryFile(delete=False)
    passwd(P.name)
    time.sleep(3)
    PLIK = open(P.name,'r')
else:
    PASSWD = sys.argv[1]
    PLIK = open(PASSWD,'r')
    P = tempfile.NamedTemporaryFile(delete=False)

   
#Parametry
WZORZEC = "^.*:(\w+[a$])\s(\D+):\/home\/students\/.*$"
WYNIK = []

if PLIK.readline() != '':
    for l in PLIK:
        wynik_wyrazenia = re.match(WZORZEC, l)
        if wynik_wyrazenia:
            WYNIK.append(wynik_wyrazenia.groups()[0])

    F = tempfile.NamedTemporaryFile(delete=False)
    if os.path.exists(F.name) == True:
        for imie in WYNIK:
            F.write(imie)
            F.write('\n')
    shell(F.name)
    PLIK.close()
    F.close()
    P.close()
    #By pliki zostaly przetworzone i usuniete
    time.sleep(0.1)
    os.remove(F.name)
    os.remove(P.name)
else:
    print "Wystapil blad"
    print "Sprawdz poprawnosc pliku lub polaczenia"
    print "I sprobuj ponownie"
