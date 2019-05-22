package com.bill.consumption.type

class SmallType {

    constructor()

    constructor(typeName : String){
        this.typeName = typeName
    }

    constructor(superType : Int , bigTypeId : Int ,typeId : Int , typeName : String){
        this.superType = superType
        this.bigTypeId = bigTypeId
        this.typeId = typeId
        this.typeName = typeName
    }

    var bigTypeId = 0 //大类ID
    var typeId = 0
    var typeName : String ?= null
    var cTime = 0L //首次创建时间
    var updateTime = 0L //更新时间(如更新名字)

    var superType = 0 //超级大类 0代表支出 1代表收入

}