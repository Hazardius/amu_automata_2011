#!/usr/bin/perl
use utf8;

my $link = $ARGV[0];

open(FILE, "$link") or die "NIE MOŻNA OTWORZYĆ PLIKU!
UŻYCIE: <nazwa_skryptu>.pl <lokalizacja_pliku_do_przetworzenia>.txt";

my @line = <FILE>;

foreach $temp (@line)
{
    if ($temp =~ /([1-3]?\d)\s(stycznia|lutego|marca|kwietnia|maja|czerwca|lipca|sierpnia|września|października|listopada|grudnia)\s([1-9]\d\D|19\d\d\D|20\d\d\D)/)
    { 
    	print "$1 $2 $3\n"
    }
}
close FILE;