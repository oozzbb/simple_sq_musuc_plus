import{_ as x,r as i,o as P,d as y,e as a,w as v,ao as U,ap as I,g as s,i as l,j as c,k as f,F as N,l as S,p as B,m as C}from"./index-c5841820.js";import{T}from"./TopWitge-92bafbde.js";import{p as V}from"./api-efb7f5c8.js";import{_ as D}from"./Input-8455744a.js";import{_ as F,a as j}from"./Space-522631d1.js";import{_ as A}from"./Switch-5810f9c6.js";const t=p=>(B("data-v-6e077acf"),p=p(),C(),p),E=t(()=>l("br",null,null,-1)),R=t(()=>l("br",null,null,-1)),W=t(()=>l("br",null,null,-1)),q=t(()=>l("br",null,null,-1)),z={class:"page"},G=t(()=>l("br",null,null,-1)),H=t(()=>l("br",null,null,-1)),J={class:"page"},K={__name:"ParserPlaylist",setup(p){let _=i(""),n=i(!1),u=i(""),r=i(""),g=()=>{V(_.value,n.value,u.value,r.value).then(d=>{d.data.code===200?window.$message.success("操作成功"):window.$message.error("操作失败："+d.data.msg)})};return(d,o)=>{const m=D,w=F,h=j,b=A,k=S;return P(),y(N,null,[a(T),E,R,W,q,a(w,{vertical:""},{default:v(()=>[a(m,{value:s(_),"onUpdate:value":o[0]||(o[0]=e=>c(_)?_.value=e:_=e),placeholder:"请输入歌单url："},null,8,["value"])]),_:1}),a(h,null,{default:v(()=>[f(" 注释:支持酷我--歌单与专辑的分享链接 ")]),_:1}),U(l("div",null,[a(m,{placeholder:"书名：",value:s(u),"onUpdate:value":o[1]||(o[1]=e=>c(u)?u.value=e:u=e)},null,8,["value"]),a(m,{placeholder:"作者:",value:s(r),"onUpdate:value":o[2]||(o[2]=e=>c(r)?r.value=e:r=e)},null,8,["value"])],512),[[I,s(n)]]),l("div",z,[f(" 是否自定义歌单信息(主要用于有声书下载方便整理)： "),a(b,{value:s(n),"onUpdate:value":o[3]||(o[3]=e=>c(n)?n.value=e:n=e)},null,8,["value"])]),G,H,l("div",J,[a(k,{onClick:s(g)},{default:v(()=>[f("提交")]),_:1},8,["onClick"])])],64)}}},Z=x(K,[["__scopeId","data-v-6e077acf"]]);export{Z as default};
