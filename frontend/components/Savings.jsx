import { useState } from "react";
import axios from "axios";
import "bootstrap/dist/css/bootstrap.min.css";

export function Savings(){
    let[accountId,setAccountId]=useState("");
    let[userId,setUserId]=useState("");
    let[message,setMessage]=useState("")
    let[success,setSuccess]=useState("");
    let[balance,setBalance]=useState("");
    
    let handleSubmit = (event) => {
        event.preventDefault();
        let store_url = 'http://localhost:8082/api/savings/deposit2';
        axios.post(store_url,{"accountId":accountId,"balance":balance})
        .then(s => {
            console.log(s);
            setMessage(`Deposit Success!  Balance is: ${s.data.balance}`);
            setSuccess(true);
            clearForm();
        }).catch(e => {
            console.log(e);
            setMessage("There was an error with your submission.");
            setSuccess(false);
        });

        

    }
    let clearForm = () => {
       setAccountId("");
       setBalance("");
    }
    return (
        <div className="container d-flex justify-content-center align-items-center vh-100" style={{ background: "linear-gradient(45deg, #233 0%, #fff 100%)" }}>
            <div className="card" style={{ width: "450px", padding: "20px" }}>
                <div className="card-body">
                    
                  
                    <form onSubmit={handleSubmit}>
                        <div className="form-group">
                            <label>AccountID</label>
                            <input type="number" value={accountId} onChange={(event) => setAccountId(event.target.value)} className="form-control form-control-sm" />
                        </div>
                        <div className="form-group">
                            <label>Amount to Deposit</label>
                            <input type="email" value={balance} onChange={(event) => setBalance(event.target.value)} className="form-control form-control-sm" />
                        </div>
                        <input type="submit" value="Register" className="btn btn-primary w-100 mt-3" />
                        </form>
</div>
</div>
</div>
    )   
}