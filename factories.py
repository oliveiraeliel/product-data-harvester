from spyder import MagazineLuizaSpyder
import json


class MagazineLuizaProducts:
    def __init__(self) -> None:
        with open('site-info.json') as file:
            data = json.load(file)
        self.data = data

    def get_products(self):
        return MagazineLuizaSpyder(
            base_url=self.data['magalu']['url'],
            target_path=self.data['magalu']['target_paths']
        ).fetch_data()
