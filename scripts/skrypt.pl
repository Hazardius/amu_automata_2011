#!/usr/bin/perl -w
use locale;
use utf8;
open(UCHWYT, "nazwiska.txt");
while (<UCHWYT>){
					chomp;
					my($number, $line) = split(/ /);
					if ($line !~/sz|cz|rz|dz|[¹æê³ñóœŸ¿]/i) { push @tab, "$line\n"; }
				}
close UCHWYT;
my @tab2 = sort {$a cmp $b} @tab;
print @tab2;
sleep;