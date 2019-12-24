function zh(fangjianhao) {
    var fjh = "";
    if (fangjianhao <= 9) {
        fjh = '0' + fangjianhao;
    } else {
        fjh = '' + fangjianhao;
    }
    return fjh;
}





function confirm(obj, roomno) {
    yywebDesign.YanjiuX.Seats.sessionValue(obj, roomno);
}

var count = 1;
var a = 1;
setInterval(function () {
        var fnum = 1;
        var cnum = 1;
        var cnums='00000001-1';
        if (count <= 4) {
            fnum = 7;
            cnums = '00000001-1';
        } else {
            fnum = Math.ceil(Math.random() * 22);
            cnum = Math.ceil(Math.random() * 4);
            switch (cnum) {
                case 1:
                    cnums = '00000001-1';
                    break;
                case 2:
                    cnums = '00000001-2';
                    break;
                case 3:
                    cnums = '00000001-3';
                    break;
                default:
                    cnums = '00000002-2';
                    break;
            }
        }
        if (a ===1) {
            confirm(zh(fnum), cnums);
            a++;
        }
        $.get('http://seat.heuet.edu.cn:8091/kyApplyLog/add.aspx', '', function () {
            confirm(zh(fnum), cnums);
        });
        count++;
        if (count === 23) {
            count = 1;
        }
    }
    , 40);