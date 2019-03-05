mini.parse();

$("#grid1").load("/getroomlist.html");

function GetData() {
    var row = grid.getSelected();
    return row;
}
function search() {
    var key = mini.get("key").getValue();
    grid.load({ key: key });
}
function onKeyEnter(e) {
    search();
}
function onRowDblClick(e) {
    onOk();
}
//////////////////////////////////
function CloseWindow(action) {
    if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
    else window.close();
}

function onOk() {
    CloseWindow("ok");
}
function onCancel() {
    CloseWindow("cancel");
}

$(".mini-grid-row").hover(function () {
    alert(1);
    $(this).addClass("mini-grid-row-hover");
},function(){
    $(this).removeClass("mini-grid-row-hover");
});
$(".mini-grid-row").on("click",function () {
    $(this).addClass("mini-grid-row-selected");
    alert(111);
});