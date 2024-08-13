import { useState } from "react";
import axios from "axios";
import "bootstrap/dist/css/bootstrap.min.css";
export function CurrentTransfer() {
    let [accountId, setAccountId] = useState("");
    let [sourceAccountId, setSourceAccountId] = useState("");
    let [targetAccountId, setTargetAccountId] = useState("")
    let [password, setPassword] = useState("");
    let [amount, setAmount] = useState("");

    let [success, setSuccess] = useState(false);
    let [message, setMessage] = useState("")

    let handleSubmit = (event) => {
        event.preventDefault();
        let store_url = `http://localhost:8082/currentaccount/transfer/${sourceAccountId}/${targetAccountId}/${amount}/${password}`;
        axios.post(store_url, { "sourceAccountId": accountId, "targetAccountId": accountId, "amount": amount, "password": password })
            .then(s => {
                console.log(s);
                setMessage(s.data);
                setSuccess(true);
                clearForm();
            }).catch(e => {
                console.log(e);
                setMessage(e.data);
                setSuccess(false);
            });



    }
    let clearForm = () => {

        setSourceAccountId("");
        setTargetAccountId("");
        setAmount("");
        setPassword("")
    }
    return (

        <div className="flex min-h-full flex-1 flex-col justify-center py-6 sm:px-6 lg:px-8">
            <div className="sm:mx-auto sm:w-full sm:max-w-md">
                <h2 className="mt-6 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">
                    Amount Transfer
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
                        <div>
                            <label htmlFor="AccountID" className="block text-sm font-medium leading-6 text-gray-900">
                                Enter Your Account ID
                            </label>
                            <div className="mt-2">
                                <input
                                    id="AccountID"
                                    name="AccountID"
                                    type="text"
                                    value={sourceAccountId}
                                    onChange={(event) => setSourceAccountId(event.target.value)}
                                    required
                                    autoComplete="AccountID"
                                    className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                />
                            </div>
                        </div>

                        <div>
                            <label htmlFor="TransferAccountID" className="block text-sm font-medium leading-6 text-gray-900">
                                Enter your Transfer Account ID
                            </label>
                            <div className="mt-2">
                                <input
                                    id="TransferAccountID"
                                    name="TransferAccountID"
                                    type="text"
                                    value={targetAccountId}
                                    onChange={(event) => setTargetAccountId(event.target.value)}
                                    required
                                    autoComplete="duration"
                                    className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                />
                            </div>
                        </div>

                        <div>
                            <label htmlFor="amounttransfer" className="block text-sm font-medium leading-6 text-gray-900">
                                Amount to transfer
                            </label>
                            <div className="mt-2">
                                <input
                                    id="amounttransfer"
                                    name="amounttransfer"
                                    type="text"
                                    value={amount}
                                    onChange={(event) => setAmount(event.target.value)}
                                    required
                                    autoComplete="amounttransfer"
                                    className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                />
                            </div>
                        </div>

                        <div>
                            <label htmlFor="Password" className="block text-sm font-medium leading-6 text-gray-900">
                                Password
                            </label>
                            <div className="mt-2">
                                <input
                                    id="Password"
                                    name="Password"
                                    type="password"
                                    required
                                    value={password}
                                    onChange={(event) => setPassword(event.target.value)}
                                    autoComplete="Password"
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

    )
}
