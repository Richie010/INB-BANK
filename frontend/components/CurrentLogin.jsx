import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";


export function CurrentLogin(){
    let[accountId,setAccountId]=useState("");
    let[password,setPassword]=useState("");
    let[message,setMessage]=useState("")
    let[success,setSuccess]=useState("");
    const navigate =useNavigate();

    let handleSubmit = async(event) => {
        event.preventDefault();
        try{
       
        const response= await axios.post('http://localhost:8082/currentaccount/login',{"accountId":accountId,"password":password})
      
            if(response.status==201){
                console.log(response);
                setMessage('Welcome user ',response.data);
                setSuccess(true);
                clearForm();
                localStorage.setItem('accountId', accountId);
                navigate("/balance");
                
            }else if(response.status==404){
                console.log(response)
                setMessage("Invalid login credentials try again");
                setSuccess(false)
            }else if(response.status==209){
                console.log(response);
                setMessage(response.data);
                setSuccess(false);
            }
       }catch(e){
        alert("invalid Login Credentials");
       }



    } 
    let clearForm = () => {
        setAccountId("")
        setPassword("");
    }
    return (
       
        <div className="flex min-h-full flex-1 flex-col justify-center py-6 sm:px-6 lg:px-8">
        <div className="sm:mx-auto sm:w-full sm:max-w-md">
            <h2 className="mt-6 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">
                Login
            </h2>
        </div>
        {message && (
                <div className={`mt-4 p-4 ${success ? 'bg-green-100 text-green-700' : 'bg-red-100 text-gray-700'} border border-${success ? 'green' : 'red'}-400 rounded`}>
                    {message}
                </div>
            )}

        <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-[480px]">
            <div className="bg-gray-800 px-6 py-12 shadow sm:rounded-lg sm:px-12">
                <form onSubmit={handleSubmit} className="space-y-6">
                    <div>
                        <label htmlFor="AccountID" className="text-sm font-medium text-white">
                            Enter Your Account ID
                        </label>
                        <div className="mt-2">
                            <input
                                id="accountId"
                                name="accountId"
                                type="number"
                                value={accountId}
                                onChange={(event) => setAccountId(event.target.value)}
                                required
                                autoComplete="AccountID"
                                className="block w-full rounded-md border-0 py-1.5 text-white-900 shadow-sm ring-1 ring-inset ring-white-300 placeholder:text-white-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                            />
                        </div>
                    </div>
                    <div>
                        <label htmlFor="password" className="text-sm font-medium text-white">
                           Password
                        </label>
                        <div className="mt-2">
                            <input
                                id="password"
                                name="password"
                                type="password"
                                value={password}
                                onChange={(event) => setPassword(event.target.value)}
                                required
                                autoComplete="duration"
                                className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                            />
                        </div>
                    </div>
                    <div>
                        <button
                            type="submit"
                            className="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
                        >
                            Login
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
        )
    
    
}