# Install postgreSQL  + PgAdmin4 before installing keycloak
# log in and create a new database for keycloak

helm repo add codecentric https://codecentric.github.io/helm-charts
helm install keycloak codecentric/keycloak -f values.yaml

# username: keycloak
# password
kubectl get secret --namespace default keycloak-http -o jsonpath="{.data.password}" | base64 --decode; echo