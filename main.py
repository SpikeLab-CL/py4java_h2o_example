from py4j.java_gateway import JavaGateway

def main():
    gateway = JavaGateway()
    prediction = gateway.predict("N",70,4)
    print(prediction)
    
if __name__ == "__main__":
    main()