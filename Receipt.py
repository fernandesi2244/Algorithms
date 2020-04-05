"""
Uses Cramer's Rule to determine the solution to the system of equations.
Each equation uses the number of each item and their total price to calculate the price per item.
"""
def findPriceOfEach(storeNo, firstItem, secondItem, noFirst_receipt1, noSecond_receipt1, totalPrice_receipt1, noFirst_receipt2, noSecond_receipt2, totalPrice_receipt2):
    print("Grocery Store #"+str(storeNo)+":")
    determinant = noFirst_receipt1*noSecond_receipt2-noFirst_receipt2*noSecond_receipt1
    if(determinant == 0):
        print("CANNOT COMPUTE PRICES")
        return
    priceFirst = (totalPrice_receipt1*noSecond_receipt2-totalPrice_receipt2*noSecond_receipt1)/determinant
    priceSecond = (noFirst_receipt1*totalPrice_receipt2-noFirst_receipt2*totalPrice_receipt1)/determinant
    print("%s: %.2f, %s: %.2f"%(firstItem, priceFirst, secondItem, priceSecond))

#Parse data from file and process it with findPriceOfEach()
scanner = open("receipt.txt", "r")
noTestCases = int(scanner.readline())
for i in range(noTestCases):
    items = scanner.readline().split()

    firstItem = items[0]
    secondItem = items[1]

    receipt1 = scanner.readline().split()
    noFirst_receipt1 = int(receipt1[0])
    noSecond_receipt1 = int(receipt1[1])
    totalPrice_receipt1 = float(receipt1[2])

    receipt2 = scanner.readline().split()
    noFirst_receipt2 = int(receipt2[0])
    noSecond_receipt2 = int(receipt2[1])
    totalPrice_receipt2 = float(receipt2[2])

    findPriceOfEach(i+1, firstItem, secondItem, noFirst_receipt1, noSecond_receipt1, totalPrice_receipt1, noFirst_receipt2, noSecond_receipt2, totalPrice_receipt2)