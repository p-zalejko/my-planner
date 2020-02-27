# see https://github.com/helm/charts/tree/master/incubator/kafka
helm repo add incubator http://storage.googleapis.com/kubernetes-charts-incubator
kubectl create ns kafka
helm install my-kafka --namespace kafka incubator/kafka
kubectl apply -f ../kafdro -n kafka