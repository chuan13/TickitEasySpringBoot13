// ========== 購物車行為 ==========

// 增加或減少數量
function ticketTypesCartChange(ticketTypeID, quantity) {
    const ticketTypesCart = JSON.parse(localStorage.getItem("ticketTypesCart"));
    if (quantity > 0) {  // 增加
        if (ticketTypesCart[ticketTypeID]) {  // 此票種已經存在於購物車內
            ticketTypesCart[ticketTypeID] += quantity;
        } else {
            ticketTypesCart[ticketTypeID] = quantity;
        }
    } else if (quantity < 0) {  // 減少
        if (ticketTypesCart[ticketTypeID]) {  // 此票種已經存在於購物車內
            if (ticketTypesCart[ticketTypeID] > Math.abs(quantity)) {  // 減少後仍有剩餘
                ticketTypesCart[ticketTypeID] -= Math.abs(quantity);
            } else {
                delete ticketTypesCart[ticketTypeID];
            }
        }
    }
    localStorage.setItem("ticketTypesCart", JSON.stringify(ticketTypesCart))
}


// ========== 初始執行 ==========
$(document).ready(function () {
    // 初始化 localStorage 內的 ticketTypesCart
    (() => {
        if (localStorage.getItem("ticketTypesCart") == null) {
            localStorage.setItem("ticketTypesCart", JSON.stringify({}));
        }
    })();






    // ========== 測試 ==========
    // (() => {
    //     console.log("==========")

    //     console.log("ticketTypesCart");
    //     console.log(JSON.parse(localStorage.getItem("ticketTypesCart")));
        
    //     console.log("ticketTypesCartChange(1, 1)")
    //     ticketTypesCartChange(1, 1);
        
    //     console.log("ticketTypesCart");
    //     console.log(JSON.parse(localStorage.getItem("ticketTypesCart")));
        
    //     console.log("ticketTypesCartChange(1, -5)")
    //     ticketTypesCartChange(1, -5);

    //     console.log("ticketTypesCart");
    //     console.log(JSON.parse(localStorage.getItem("ticketTypesCart")));
    // })();

    
    
    // const checkoutTicketTypeCart = [
    //     {
    //         ticketTypeID: 1,
    //         eventName: "活動01",
    //         ticketTypeName: "票種01",
    //         eventPic: "/image/event/1.jpg",
    //         quantity: 3,
    //         price: 100,
    //         totalPrice: 300
    //     },
    //     {
    //         ticketTypeID: 2,
    //         eventName: "活動01",
    //         ticketTypeName: "票種02",
    //         eventPic: "/image/event/1.jpg",
    //         quantity: 2,
    //         price: 120,
    //         totalPrice: 240
    //     },
    // ];
    
    // localStorage.setItem("ticketTypeCart", JSON.stringify(ticketTypeCart));




});