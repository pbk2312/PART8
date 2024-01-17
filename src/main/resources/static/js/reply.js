async function getList({bno, page, size, goLast}) {

    const result = await axios.get(`/replies/list/${bno}`, {params: {page, size}})

    if (goLast) {
        const total = result.data.total
        const lastPage = parseInt(Math.ceil(total / size))
        return getList({bno: bno, page: lastPage, size: size})
    }

    return result.data

}

async function addReply(replyObj) {
    const response = await axios.post(`/replies/`, replyObj)
    return response.data
} // //addReply()가 정상적으로 처리되면 서버에서는 '{'rno':11}' 과 같은 JSON 데이터를 전송하게 됩니다.이를 이용해서 댓글이 추가되면 경고창을 보여주고
// 마지막 페이지로 이동해서 등록된 댓글을 볼 수 있게 구성합니다.

async function getReply(rno) {
    const response = await axios.get(`/replies/${rno}`)
    return response.data
}

async function modifyReply(replyObj) {
    const response = await axios.put(`/replies/${replyObj.rno}`, replyObj)
    return response.data
}

async function removeReply(rno) {
    const response = await axios.delete(`/replies/${rno}`)
    return response.data
}
