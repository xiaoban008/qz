package com.xiaoban.pojo;

/**
 * @Description:
 * @Author: xiaoban
 * @Date: 2019/7/1 3:13 PM
 */
public class GradeInfo {
    private String nianfen;
    private String xueqi;
    private String daihao;
    private String kcmc;    //课程名称


    private String kcxz;    //课程性质
    private String pscj;    //平时
    private String kscj;    //考试
    private String zf;

    @Override
    public String toString() {
        return "GradeInfo{" +
                "nianfen='" + nianfen + '\'' +
                ", xueqi='" + xueqi + '\'' +
                ", daihao='" + daihao + '\'' +
                ", kcmc='" + kcmc + '\'' +
                ", kcxz='" + kcxz + '\'' +
                ", pscj='" + pscj + '\'' +
                ", kscj='" + kscj + '\'' +
                ", zf='" + zf + '\'' +
                '}';
    }

    public static void main(String[] args) {
        GradeInfo g = new GradeInfo();
        String a = "2018-2019 2 07401933 计算机系统结构 必修   3.0 3.00 87.5   79   82 0       信息技术学院";
        a=a.replace("   "," ");

        g.setNianfen(a.split(" ")[0]);
        g.setXueqi(a.split(" ")[1]);
        g.setDaihao(a.split(" ")[2]);
        g.setKcmc(a.split(" ")[3]);
        g.setKcxz(a.split(" ")[4]);
        g.setPscj(a.split(" ")[7]);
        g.setKscj(a.split(" ")[7]);
        g.setZf(a.split(" ")[7]);

        System.out.println(g);
    }
    public String getNianfen() {
        return nianfen;
    }

    public void setNianfen(String nianfen) {
        this.nianfen = nianfen;
    }

    public String getXueqi() {
        return xueqi;
    }

    public void setXueqi(String xueqi) {
        this.xueqi = xueqi;
    }

    public String getDaihao() {
        return daihao;
    }

    public void setDaihao(String daihao) {
        this.daihao = daihao;
    }

    public String getKcmc() {
        return kcmc;
    }

    public void setKcmc(String kcmc) {
        this.kcmc = kcmc;
    }

    public String getKcxz() {
        return kcxz;
    }

    public void setKcxz(String kcxz) {
        this.kcxz = kcxz;
    }

    public String getPscj() {
        return pscj;
    }

    public void setPscj(String pscj) {
        this.pscj = pscj;
    }

    public String getKscj() {
        return kscj;
    }

    public void setKscj(String kscj) {
        this.kscj = kscj;
    }

    public String getZf() {
        return zf;
    }

    public void setZf(String zf) {
        this.zf = zf;
    }

}
