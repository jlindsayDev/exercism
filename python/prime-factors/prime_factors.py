from math import sqrt

def factors(value):
    primes = primes_generator()
    factor_list = []

    num = next(primes)
    while value >= num:
        if value % num == 0:
            factor_list.append(num)
            value //= num
        else:
            num = next(primes)

    return factor_list

def primes_generator():
    yield 2

    num = 1
    while True:
        num += 2
        if not any(num % n == 0 for n in range(3, int(sqrt(num)), 2)):
            yield num
