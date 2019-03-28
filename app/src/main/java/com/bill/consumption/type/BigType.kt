package com.bill.consumption.type

class BigType {

    constructor()

    constructor(typeName : String){
        this.typeName = typeName
    }

    constructor(typeId : Int , typeName : String){
        this.typeId = typeId
        this.typeName = typeName
    }

    var typeId = 0
    var typeName : String ?= null
    var cTime = 0L //首次创建时间
    var updateTime = 0L //更新时间(如更新名字)


}