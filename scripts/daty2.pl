#!/usr/bin/perl

if ($#ARGV < 0)
{
	die "Aby uruchomiæ napisz: perl daty.pl <plik wejsciowy>\n";
}

open(plik, $ARGV[0]) || die "Nie mozna otworzyc pliku '".$ARGV[0]."'\n";

while ($data = <plik>) 
{
  if ($data =~ m/^(0[1-9]|[1-2][0-9]|3[0-1])-(0[1-9]|1[0-2])-19([0-9]{2})\s*$/)
  {
      $dzien = $1;
      $miesiac = $2;
      $rok = $3;

      if ($miesiac =~ m/01|03|05|07|08|10|12/)
      {
        if ($dzien <= 31)
        {
          print $data;
        }
      }

      if ($miesiac =~ m/04|06|09|11/)
      {
        if ($dzien <= 30)
        {
          print $data;
        }
      }

      if ($miesiac =~ m/02/)
      {
        if ($rok % 4 == 0)
        { 
          if ($dzien <= 29)
          {
            print $data;
          }
        }
        else
        {
          if ($dzien <= 28)
          {
            print $data;
          }
        }
      }
  }
}

close(plik);