wget https://github.com/strimzi/strimzi-kafka-operator/releases/download/0.16.2/strimzi-0.16.2.zip

unzip strimzi-0.16.2.zip

kubectl create ns kafka
sed -i 's/namespace: .*/namespace: kafka/' strimzi-0.16.2/install/cluster-operator/*RoleBinding*.yaml

kubectl apply -f strimzi-0.16.2/install/cluster-operator/ -n kafka
kubectl apply -f strimzi-0.16.2/install/cluster-operator/020-RoleBinding-strimzi-cluster-operator.yaml -n kafka
kubectl apply -f strimzi-0.16.2/install/cluster-operator/032-RoleBinding-strimzi-cluster-operator-topic-operator-delegation.yaml -n kafka
kubectl apply -f strimzi-0.16.2/install/cluster-operator/031-RoleBinding-strimzi-cluster-operator-entity-operator-delegation.yaml -n kafka
kubectl create -f cluster.yaml -n kafka