divert(-1)
/*
 * Rules of sudoku
 * $Id: sudoku.m4 479 2008-08-12 05:21:53Z brenz $
 */
include(`util.mml')dnl
include(`logic.mml')dnl
/*
 * Encoding of sudoku: 
 * 1. cijk means that cell (i,j) has value k
 * 2. Each cell has exactly one value (Rule1)
 * 3. The values of each row, each column and each region
 *    are pairwise different (Rule2)
 */
divert
// Rule1 
for(`i',`1',`9',`for(`j',`1',`9',`ifdef(`@lastj',`oneOf(for(`k',`1',`9',`c`'i`'j`'k`'ifdef(`@lastk',`',`,')')) 
',`oneOf(for(`k',`1',`9',`c`'i`'j`'k`'ifdef(`@lastk',`',`,')')) & 
')') &') 
// Rule2
define(`different',`for(`x',`1',`9',`maxOneOf($1`'x,$2`'x,$3`'x,$4`'x,$5`'x,$6`'x,$7`'x,$8`'x,$9`'x)ifdef(`@lastx',`', &`')')')
// rows
for(`i',`1',`9',`different(for(`j',`1',`9',`c`'i`'j`',')) &
')
// columns
for(`j',`1',`9',`different(for(`i',`1',`9',`c`'i`'j`',')) &
')
// regions
different(`c11',`c12',`c13',`c21',`c22',`c23',`c31',`c32',`c33') &
different(`c41',`c42',`c43',`c51',`c52',`c53',`c61',`c62',`c63') &
different(`c71',`c72',`c73',`c81',`c82',`c83',`c91',`c92',`c93') &

different(`c14',`c15',`c16',`c24',`c25',`c26',`c34',`c35',`c36') &
different(`c44',`c45',`c46',`c54',`c55',`c56',`c64',`c65',`c66') &
different(`c74',`c75',`c76',`c84',`c85',`c86',`c94',`c95',`c96') &

different(`c17',`c18',`c19',`c27',`c28',`c29',`c37',`c38',`c39') &
different(`c47',`c48',`c49',`c57',`c58',`c59',`c67',`c68',`c69') &
different(`c77',`c78',`c79',`c87',`c88',`c89',`c97',`c98',`c99')

