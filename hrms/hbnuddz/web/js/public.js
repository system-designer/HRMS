/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * 给指定ID的iframe自适应其body的高度
 * @param down iframe的ID
 * @return null
 */
function dyniframesize(pTar) {
    pTar.height = pTar.contentWindow.document.body.scrollHeight;
}

