#!/usr/bin/perl
use utf8;
use warnings;
use locale;

open(PLIK, "<" ,"/etc/passwd");
while(<PLIK>){
  chomp;
  my($x, $y, $z, $w, $os, $link, $e) = split(/:/);
  if ($link=/\/home\/students.*/){
    if ($os =~ /(\w{2,}[^a])\s+.*/){
      push @tablica, $1;
    }
  }
}
close PLIK;
@tablica = sort @tablica;
my %licz = ();
for (@tablica){
$licz{$_}++;
}
foreach my $klucze (keys %licz){
push @powt,"$licz{$klucze} $klucze";
}
@powt = reverse sort @powt;
if ($powt[0] =~ m/\d+\s+(.*)/){
print "$1 \n";
}
