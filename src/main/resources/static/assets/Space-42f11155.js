import{s as O,aa as $,y as c,$ as l,z as B,A as R,D as y,C as P,E as k,L as p,F as D,cg as L,ch as M,n as T,aJ as I,ab as V,ci as A,O as F,bi as W,af as S}from"./index-5d122f12.js";function H(e,t="default",a=[]){const i=e.$slots[t];return i===void 0?a:i()}const J=O("divider",`
 position: relative;
 display: flex;
 width: 100%;
 box-sizing: border-box;
 font-size: 16px;
 color: var(--n-text-color);
 transition:
 color .3s var(--n-bezier),
 background-color .3s var(--n-bezier);
`,[$("vertical",`
 margin-top: 24px;
 margin-bottom: 24px;
 `,[$("no-title",`
 display: flex;
 align-items: center;
 `)]),c("title",`
 display: flex;
 align-items: center;
 margin-left: 12px;
 margin-right: 12px;
 white-space: nowrap;
 font-weight: var(--n-font-weight);
 `),l("title-position-left",[c("line",[l("left",{width:"28px"})])]),l("title-position-right",[c("line",[l("right",{width:"28px"})])]),l("dashed",[c("line",`
 background-color: #0000;
 height: 0px;
 width: 100%;
 border-style: dashed;
 border-width: 1px 0 0;
 `)]),l("vertical",`
 display: inline-block;
 height: 1em;
 margin: 0 8px;
 vertical-align: middle;
 width: 1px;
 `),c("line",`
 border: none;
 transition: background-color .3s var(--n-bezier), border-color .3s var(--n-bezier);
 height: 1px;
 width: 100%;
 margin: 0;
 `),$("dashed",[c("line",{backgroundColor:"var(--n-color)"})]),l("dashed",[c("line",{borderColor:"var(--n-color)"})]),l("vertical",{backgroundColor:"var(--n-color)"})]),N=Object.assign(Object.assign({},y.props),{titlePlacement:{type:String,default:"center"},dashed:Boolean,vertical:Boolean}),Z=B({name:"Divider",props:N,setup(e){const{mergedClsPrefixRef:t,inlineThemeDisabled:a}=R(e),s=y("Divider","-divider",J,L,e,t),i=P(()=>{const{common:{cubicBezierEaseInOut:r},self:{color:o,textColor:m,fontWeight:h}}=s.value;return{"--n-bezier":r,"--n-color":o,"--n-text-color":m,"--n-font-weight":h}}),n=a?k("divider",void 0,i,e):void 0;return{mergedClsPrefix:t,cssVars:a?void 0:i,themeClass:n==null?void 0:n.themeClass,onRender:n==null?void 0:n.onRender}},render(){var e;const{$slots:t,titlePlacement:a,vertical:s,dashed:i,cssVars:n,mergedClsPrefix:r}=this;return(e=this.onRender)===null||e===void 0||e.call(this),p("div",{role:"separator",class:[`${r}-divider`,this.themeClass,{[`${r}-divider--vertical`]:s,[`${r}-divider--no-title`]:!t.default,[`${r}-divider--dashed`]:i,[`${r}-divider--title-position-${a}`]:t.default&&a}],style:n},s?null:p("div",{class:`${r}-divider__line ${r}-divider__line--left`}),!s&&t.default?p(D,null,p("div",{class:`${r}-divider__title`},this.$slots),p("div",{class:`${r}-divider__line ${r}-divider__line--right`})):null)}});function U(){return M}const K={name:"Space",self:U},q=K;let C;function Q(){if(!T)return!0;if(C===void 0){const e=document.createElement("div");e.style.display="flex",e.style.flexDirection="column",e.style.rowGap="1px",e.appendChild(document.createElement("div")),e.appendChild(document.createElement("div")),document.body.appendChild(e);const t=e.scrollHeight===1;return document.body.removeChild(e),C=t}return C}const X=Object.assign(Object.assign({},y.props),{align:String,justify:{type:String,default:"start"},inline:Boolean,vertical:Boolean,reverse:Boolean,size:{type:[String,Number,Array],default:"medium"},wrapItem:{type:Boolean,default:!0},itemClass:String,itemStyle:[String,Object],wrap:{type:Boolean,default:!0},internalUseGap:{type:Boolean,default:void 0}}),ee=B({name:"Space",props:X,setup(e){const{mergedClsPrefixRef:t,mergedRtlRef:a}=R(e),s=y("Space","-space",void 0,q,e,t),i=I("Space",a,t);return{useGap:Q(),rtlEnabled:i,mergedClsPrefix:t,margin:P(()=>{const{size:n}=e;if(Array.isArray(n))return{horizontal:n[0],vertical:n[1]};if(typeof n=="number")return{horizontal:n,vertical:n};const{self:{[F("gap",n)]:r}}=s.value,{row:o,col:m}=W(r);return{horizontal:S(m),vertical:S(o)}})}},render(){const{vertical:e,reverse:t,align:a,inline:s,justify:i,itemClass:n,itemStyle:r,margin:o,wrap:m,mergedClsPrefix:h,rtlEnabled:_,useGap:g,wrapItem:E,internalUseGap:j}=this,v=V(H(this),!1);if(!v.length)return null;const z=`${o.horizontal}px`,x=`${o.horizontal/2}px`,G=`${o.vertical}px`,u=`${o.vertical/2}px`,f=v.length-1,b=i.startsWith("space-");return p("div",{role:"none",class:[`${h}-space`,_&&`${h}-space--rtl`],style:{display:s?"inline-flex":"flex",flexDirection:(()=>e&&!t?"column":e&&t?"column-reverse":!e&&t?"row-reverse":"row")(),justifyContent:["start","end"].includes(i)?`flex-${i}`:i,flexWrap:!m||e?"nowrap":"wrap",marginTop:g||e?"":`-${u}`,marginBottom:g||e?"":`-${u}`,alignItems:a,gap:g?`${o.vertical}px ${o.horizontal}px`:""}},!E&&(g||j)?v:v.map((w,d)=>w.type===A?w:p("div",{role:"none",class:n,style:[r,{maxWidth:"100%"},g?"":e?{marginBottom:d!==f?G:""}:_?{marginLeft:b?i==="space-between"&&d===f?"":x:d!==f?z:"",marginRight:b?i==="space-between"&&d===0?"":x:"",paddingTop:u,paddingBottom:u}:{marginRight:b?i==="space-between"&&d===f?"":x:d!==f?z:"",marginLeft:b?i==="space-between"&&d===0?"":x:"",paddingTop:u,paddingBottom:u}]},w)))}});export{ee as _,Z as a,H as g};
