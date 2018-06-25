import os.path
from ckanapi import RemoteCKAN

cacheFolder=os.path.abspath(os.path.join(os.path.dirname(os.path.realpath(__file__)), "../../data/opencanadaca"))
ckanApiUrl='http://open.canada.ca/data/en/api'
ckanBaseUrl='http://open.canada.ca/data/en/'
ckanClient = CkanClient(base_location=ckanApiUrl)