#!/usr/bin/perl -w
use locale;
use utf8;


open(UCHWYT, "nazwiska.txt") or die "NIE MO¯NA OTWORZYÆ PLIKU!";
while (<UCHWYT>) {
	$wiersz = $_;
	if ($wiersz =~ /^(sk(a|i)$)|(ck(a|i)$)|(dzk(a|i)$)/) {
	push @tab, $wiersz;
	}
}
close UCHWYT;

# sortuje tworzac liste frekwencyjna
my @tab2 = reverse sort { $^W=0; $a <=> $b or $a cmp $b } @tab;


for ($i=0; $i < 1000; $i++) {
	print $tab2[$i];
}