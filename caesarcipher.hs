import Data.List
import Data.Char

let2nat :: Char -> Int
let2nat c = (ord c) - (ord 'a')

nat2let :: Int -> Char
nat2let num = chr (num + ord 'a')

shift :: Int -> Char -> Char
shift num c | isLower c = nat2let((let2nat c + num) `mod` 26)
            | otherwise = c

encode :: Int -> String -> String
encode n s = [shift n x | x <- s]

decode :: Int -> String -> String
decode n s = [shift (n-nn) x | x <- s]
    where nn = 2 * n

table::[Float]                        
table = [8.2, 1.5, 2.8, 4.3, 12.7, 2.2, 2.0, 6.1, 7.0,
         0.2, 0.8, 4.0, 2.4, 6.7, 7.5, 1.9, 0.1, 6.0,
         6.3, 9.1, 2.8, 1.0, 2.4, 0.2, 2.0, 0.1]

lowers :: String -> Int
lowers s = length[ x | x <- s, isLower x]

count :: Char -> String -> Int
count x s = length[ x' | x' <- s, x == x']

percent :: Int -> Int -> Float
percent a b = (fromIntegral a / fromIntegral b) * 100

freqs :: String -> [Float]
freqs s = [percent(count x s) n | x <- ['a' .. 'z']]
    where n = lowers s

rotate :: Int -> [a] -> [a] 
rotate n xs = drop n xs ++ take n xs

chisqr :: [Float] -> [Float] -> Float
chisqr observed expected = sum [(a - b)^2/b | (a,b) <- zip observed expected]

position :: Eq a => a -> [a] -> Int
position x l = head [ b | (a,b) <- zip l [0..(length l -1)], a==x ]

crack :: String -> String
crack s = encode (-num) s
    where 
        num = head (positions (minimum chi) chi)
        chi = [chisqr(rotate n table') table | n <- [0 .. 25]]
        table' = freqs s
        positions x s = [i | (x',i) <- zip s [0 .. n], x==x']
            where n = length s - 1