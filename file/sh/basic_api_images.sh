cd /root/github/share-platform-maven
mvn clean compile package -Dmaven.test.skip=true
cd /root/github/share-platform-maven/share-basic/share-api
docker build -f src/main/resources/Dockerfile -t share-api:1.0.0 .
docker tag share-api:1.0.0 harbor.andot.org/lucas/share-api:1.0.0
docker push harbor.andot.org/lucas/share-api:1.0.0
cd /root/github/share-platform-maven/share-basic/share-api/src/main/resources/
kubectl delete -f share-api.yaml
kubectl apply -f share-api.yaml
#  restart k8s con
kubectl scale deployment share-api --replicas=0 -n share-app
kubectl scale deployment share-api --replicas=1 -n share-app

# kubectl exec -it share-app-im-5c4cd75ddf-qdqk8 -n share-app -- /bin/bash
# kubectl logs -f share-app-im-5c4cd75ddf-qdqk8 -n share-app


kubectl apply -f - <<EOF
apiVersion: v1
kind: ConfigMap
metadata:
  namespace: metallb-system
  name: config
data:
  config: |
    address-pools:
    - name: default
      protocol: layer2
      addresses:
      - 192.168.1.100-192.169.1.199
EOF