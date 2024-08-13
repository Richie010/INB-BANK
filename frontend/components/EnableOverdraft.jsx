import { useState, useEffect } from "react";
import axios from "axios";
import "bootstrap/dist/css/bootstrap.min.css";

export function EnableOverdraft() {
    const [accountId, setAccountId] = useState("");
    const [enable, setEnabled] = useState(""); 
    const [success, setSuccess] = useState(false);
    const [message, setMessage] = useState("");

    useEffect(() => {
       
        const storedAccountId = localStorage.getItem("accountId");
        if (storedAccountId) {
            setAccountId(storedAccountId);
        } else {
            setMessage("No account ID found in local storage.");
        }
    }, []);

    const handleSubmit = (event) => {
        event.preventDefault();
        const storeUrl = `http://localhost:8082/currentaccount/${accountId}/enableOverdraft`;
        const data = { enable: enable === "true" }; 

        axios.post(storeUrl, data)
            .then(response => {
                console.log(response);
                setMessage("Overdraft status updated successfully");
                setSuccess(true);
                clearForm();
            })
            .catch(error => {
                console.log(error);
                setMessage("There was an error with your submission.");
                setSuccess(false);
            });
    };

    const clearForm = () => {
        setEnabled(""); 
    };

    return (
        <div className="flex min-h-full flex-1 flex-col justify-center py-6 sm:px-6 lg:px-8">
        <div className="sm:mx-auto sm:w-full sm:max-w-md">
            <h2 className="mt-6 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">
                Enable OverdraftBalance 
            </h2>
        </div>

        <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-[480px]">
            <div className="bg-white px-6 py-12 shadow sm:rounded-lg sm:px-12">
            <form onSubmit={handleSubmit}className="space-y-6">
                <div className="form-group">
                    <label htmlFor="enabled">Enable Overdraft (true/false)</label>
                    <input
                        type="text"
                        className="form-control"
                        id="enabled"
                        value={enable}
                        onChange={(e) => setEnabled(e.target.value)}
                        placeholder="Enter true or false"
                    />
                </div>
                <button type="submit" className="btn btn-primary mt-2">Submit</button>
            </form>
            {message && (
                <div className={`alert mt-3 ${success ? 'alert-success' : 'alert-danger'}`} role="alert">
                    {message}
                </div>
            )}
        </div>
        </div>
        </div>
    );
}
