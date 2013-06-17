#!/usr/bin/perl
use locale;

if($ARGV[0]=~/(^http:\/\/www.wmi.amu.edu.pl\/Studia\/Studiazaoczne\/Planzaj%C4%99%C4%87\/tabid\/239\/Default.aspx$)/)
{
open(FILE, "plan.txt") || die("b³¢d odczytu pliku!!!\n");
@tekst = <FILE>;
close(FILE);

foreach $line(@tekst) 
{
	if($line=~/(1[0-1].[0-5]\d|0\d.[0-5]\d)\s*A1-/)
	{		
		@words = split(/(\s)/, $line);
		foreach $word(@words)
		{
			if(($word =~ /(([A-Z]{3}([A-Z]|\d{0,3}))|([A-Z]{2}\d))/)&& !($word =~ /^XML$/))
			{
				print ($word," ");											
			}
		}
	print "\n";
	}
}
}
else
{
	print "To nie jest prawid³owy adres!\n"
}