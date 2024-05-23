def factors(value):
    factor_list = []

    while value % 2 == 0:
        factor_list.append(2)
        value //= 2

    factor = 3
    while value >= factor:
        if value % factor == 0:
            factor_list.append(factor)
            value //= factor
        else:
            factor += 2

    return factor_list
