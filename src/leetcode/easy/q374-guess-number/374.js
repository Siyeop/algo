/**
 * Forward declaration of guess API.
 * @param {number} num   your guess
 * @return 	     -1 if num is higher than the picked number
 *			      1 if num is lower than the picked number
 *               otherwise return 0
 * var guess = function(num) {}
 */
let getGuessFunction = (pick) => {
    return (num) => {
        if(num > pick) {
            return -1;
        } else if (num === pick) {
            return 0;
        } else {
            return 1;
        }
    }
}

/**
 * @param {number} n
 * @return {number}
 */
var guessNumber = function(n) {
    let min = 0;
    let max = n;
    let curr = Number.parseInt(n/2);
    let result = guess(curr);
    while(result !== 0) {
        if(result === -1) { // curr > pick
            max = curr - 1;
        } else { // curr > num
            min = curr + 1;
        }
        curr = Number.parseInt((min + max) / 2);
        result = guess(curr);
    }
    return curr;
};


let guess = getGuessFunction(6);
console.log(guessNumber(10));
