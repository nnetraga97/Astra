class Message{

     boardid;

    userid;
    
    data;

	create_dt;


    Message(boardid,userid,data){
        this.boardid=boardid;
        this.userid=userid;
        this.data=data;
		this.create_dt = Date.prototype.getTime();
    }
	 getBoardid() {
		return this.boardid;
	}

	 setBoardid(boardid) {
		this.boardid = boardid;
	}

	getUserid() {
		return this.userid;
	}

	setUserid(userid) {
		this.userid = userid;
	}

	 getData() {
		return this.data;
	}

	 setData(data) {
		this.data = data;
	}

}

export default Message;