"""
@author : Tien Nguyen
@date   : 2023-06-16
"""
from sklearn.model_selection import train_test_split

def split_train_test(
        data: list, 
        test_size: int,
        seed: int, 
    ) -> tuple:
    X_train, X_test = train_test_split(data, test_size=test_size,\
                                            random_state=seed, shuffle=False)
    return X_train, X_test
