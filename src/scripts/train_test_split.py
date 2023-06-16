"""
@author : Tien Nguyen
@date   : 2023-06-16
@desc   : 
        - Split data into train and test set
        - Store train and test set in separate directories

"""
import os
import sys
import argparse

cwd = os.path.split(os.getcwd())[0]
cwd = os.path.join(cwd, 'src')

os.chdir(cwd)
sys.path.append(cwd)

import utils
import constants
from configs import Configurer

def load_image_files(
        data_dir: str,
        configs: Configurer
    ) -> list:
    file_path = utils.join_path((data_dir, '*', f'*.{configs.img_format}'))
    return utils.list_files(file_path)

def create_subdir(
        args: argparse.Namespace,
        categories: list
    ) -> None:
    utils.mkdir(utils.join_path((args.saved_dir, constants.TRAIN)))
    utils.mkdir(utils.join_path((args.saved_dir, constants.TEST)))
    for category in categories:
        utils.mkdir(utils.join_path((args.saved_dir, constants.TRAIN, category)))
        utils.mkdir(utils.join_path((args.saved_dir, constants.TEST, category)))

def prepare_data(
        args: argparse.Namespace,
        configs: Configurer
    ) -> dict:
    data = {}
    image_files = load_image_files(args.data_dir, configs)
    for image_file in image_files:
        category = utils.segment_path(image_file)[-2]
        if category not in data:
            data[category] = [image_file]
        else:
            data[category].append(image_file)
    return data

def arrange_data(
        phase: str,
        category: str,
        saved_dir: str,
        image_files: list,    
    ):
    for image_file in image_files:
        basename, file_ext = utils.get_path_basename(image_file)
        saved_file = f'{basename}.{file_ext}'
        saved_file = utils.join_path((saved_dir, phase, category, saved_file))
        utils.copy_file(image_file, saved_file)

def split_train_test(
        args: argparse.Namespace,
        configs: Configurer
    ):
    data = prepare_data(args, configs)
    categories = list(data.keys())
    create_subdir(args, categories)
    for category, list_files in data.items():
        X_train, X_test = utils.split_train_test(list_files, configs.test_rate,\
                                                                configs.seed)
        arrange_data(constants.TRAIN, category, args.saved_dir, X_train)
        arrange_data(constants.TEST, category, args.saved_dir, X_test)

if __name__ == '__main__':
    parser = argparse.ArgumentParser()
    parser.add_argument('--data_dir', type=str,\
                                        help='Directory of the data source')
    parser.add_argument('--saved_dir', type=str,\
                                            help='Output directory')
    parser.add_argument('--configs', type=str, default='configs.yaml',\
                                                    help='configuration file')

    args = parser.parse_args()
    configs = Configurer(args.configs)
    split_train_test(args, configs)
