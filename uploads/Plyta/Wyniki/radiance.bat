oconv room.rad > room.oct

rvu -vp 0  0  2.725 -vd 0.5 0.5 -0.5 -av .0 .0 .0 room.oct

REM rtrace -ov room.oct < tr.inp | rcalc −e $1=47.4*$1+120*$2+11.6*$3

