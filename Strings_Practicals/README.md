# Knuth Morris Pratt

## How it works:

searches for occurrences of a "word" W within a main "text string" S 
by employing the observation that when a mismatch occurs, the word itself 
embodies sufficient information to determine where the next match could begin, 
thus bypassing re-examination of previously matched characters.
   
# String Rotation

## How it works:

a string s = uv is said to be a rotation of t if t = vu.