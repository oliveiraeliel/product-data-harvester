from spyder import Spyder


def main():
    print(Spyder('https://www.magazineluiza.com.br/',
                 'celulares-e-smartphones/l/te').fetch_product())


if __name__ == '__main__':
    main()
