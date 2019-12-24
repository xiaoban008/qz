function get_all() {
    let all = $(".box-content a");
    let list = [];
    $.each(all, function () {
        let temp = $(this).attr("onclick");
        if (temp != null) {
            // let ret = temp.split(",");
            // console.log(ret[0]);
            list.push($(this));
        }
        // $(this).click();
    });
    return list;
}

function run(){
    for (let i = 0; i < 23; i++) {
        let a = get_all()[i];
        let s = $(a).find("span").text();
        console.log(s);
        // $(a).click();
    }
}
setInterval(function () {
    run();
}, 1000);