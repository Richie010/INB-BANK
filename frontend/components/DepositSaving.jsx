import { useState, useEffect } from "react";
import axios from "axios";
import "bootstrap/dist/css/bootstrap.min.css";

export function DepositSaving() {
    let [accountId, setAccountId] = useState("");
    let [balance, setBalance] = useState("");
    let [success, setSuccess] = useState(false);
    let[password,setPassword]=useState("");
    let [message, setMessage] = useState("");

    useEffect(() => {
        // Retrieve the accountId from local storage when the component mounts
        const storedAccountId = localStorage.getItem("accountId");
        if (storedAccountId) {
            setAccountId(storedAccountId);
        } else {
            setMessage("No account ID found in local storage.");
        }
    }, []);

    let handleSubmit = (event) => {
        event.preventDefault();
        let store_url = 'http://localhost:8082/api/savings/deposit2';
        axios.post(store_url, { "accountId": accountId, "password":password,"balance": balance })
            .then(s => {
                console.log(s);
                setMessage(`Deposit Success!`);
                setSuccess(true);
                clearForm();
            }).catch(e => {
                console.log(e);
                setMessage("There was an error with your submission.");
                setSuccess(false);
            });
    };

    let clearForm = () => {
        setBalance("");
        setPassword("");
    };

    return (
        <>
            <div className="flex min-h-full flex-1 flex-col justify-center py-6 sm:px-6 lg:px-8">
                <div className="sm:mx-auto sm:w-full sm:max-w-md">
                    <h2 className="mt-6 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">
                        Deposit Amount
                    </h2>
                    {message && (
                        <div className={`alert ${success ? 'alert-success' : 'alert-danger'} text-center`}>
                            {message}
                        </div>
                    )}
                </div>

                <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-[480px]">
                    <div className="bg-white px-6 py-12 shadow sm:rounded-lg sm:px-12">
                        <form onSubmit={handleSubmit} className="space-y-6">
                            {message && (
                                <div className="text-sm font-medium leading-6 text-gray-900">
                                    {message}
                                </div>
                            )}
                            <div>
                                <label htmlFor="AmountToDeposit" className="block text-sm font-medium leading-6 text-gray-900">
                                    Amount to Deposit
                                </label>
                                <div className="mt-2">
                                    <input
                                        id="AmountToDeposit"
                                        name="AmountToDeposit"
                                        type="text"
                                        required
                                        value={balance}
                                        onChange={(event) => setBalance(event.target.value)}
                                        autoComplete="AmountToDeposit"
                                        className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                    />
                                </div>
                                <label htmlFor="AmountToDeposit" className="block text-sm font-medium leading-6 text-gray-900">
                                   Enter your Pin
                                </label>
                                <div className="mt-2">
                                    <input
                                        id="password"
                                        name="password"
                                        type="password"
                                        required
                                        value={password}
                                        onChange={(event) => setPassword(event.target.value)}
                                        autoComplete="password"
                                        className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                    />
                                </div>
                            </div>

                            <div>
                                <button
                                    type="submit"
                                    className="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
                                >
                                    Submit
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </>
    );
}
