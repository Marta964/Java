import axios from "axios";
import React, { useState} from "react";
import {  useNavigate,useParams  } from "react-router-dom";

export default function AddConversion(){
    let navigate = useNavigate();
    const { id } = useParams();

    const [conv,setConv] = useState({
        amountFrom:"",
    });

    const {amountFrom} = conv;

    const onInputChange=(e)=>{
        setConv({...conv,[e.target.name]:e.target.value});
    };

    const onSubmit = async(e)=>{
        e.preventDefault();
        await axios.post(`http://localhost:8080/convert/${id}`,amountFrom);
        navigate(`view/${id}`);
    };

    return(
        <div className="container">
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-4">Add Convertation</h2>

                    <form onSubmit={(e)=>onSubmit(e)}>
                        <div className="mb-3">
                            <label htmlFor="AmountFrom" className="form-label">
                                AmountFrom
                            </label>
                            <input
                            type={"text"}
                            className="form-control"
                            placeholder="Enter amount from"
                            name="amountFrom"
                            value={amountFrom}
                            onChange={(e)=> onInputChange(e)}
                            />
                        </div>
                        <button type = "submit" className="btn btn-outline-primary">
                            Add
                        </button>
                    </form>
                </div>
            </div>
        </div>
    )
}