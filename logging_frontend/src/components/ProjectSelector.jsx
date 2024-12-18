import React, { useEffect, useState } from 'react'
import { AiOutlineSearch } from 'react-icons/ai';
import { BiChevronDown } from 'react-icons/bi';
import { useDispatch, useSelector } from 'react-redux';
import { fetchProjectName } from '../slices/LogSlice';
import { useNavigate } from 'react-router-dom';

export default function ProjectSelector() {

  const navigator = useNavigate();
  const dispatcher = useDispatch();
  const data = useSelector(state => state.log.projectName);

  const [inputValue, setInputValue] = useState("");
  const [selected, setSelected] = useState("");
  const [open, setOpen] = useState(false);

  useEffect(() => {
    dispatcher(fetchProjectName())
  }, []);

  return (
    <div className='bg-gray-900 font-Poppins flex  h-screen items-center justify-center'>
    <div className="flex items-center justify-center h-screen ">
      <div className="w-80 font-medium">
        {/* Dropdown Header */}
        <div
          onClick={() => setOpen(!open)}
          className={`bg-white w-full p-4 flex items-center justify-between rounded-lg border border-gray-300 shadow-md cursor-pointer hover:border-gray-400 ${
            !selected ? "text-gray-500" : "text-gray-900"
          }`}
        >
          {selected
            ? selected.length > 25
              ? selected.substring(0, 25) + "..."
              : selected
            : "Select Project"}
          <BiChevronDown
            size={24}
            className={`transform transition-transform duration-300 ${
              open ? "rotate-180" : ""
            }`}
          />
        </div>

        {/* Dropdown List */}
        <ul
          className={`bg-white mt-2 rounded-lg shadow-lg border border-gray-300 transition-all duration-300 overflow-hidden ${
            open ? "max-h-60" : "max-h-0"
          }`}
        >
          {/* Search Bar */}
          <div className="flex items-center px-4 py-3 sticky top-0 bg-white z-10 border-b border-gray-200">
            <AiOutlineSearch size={20} className="text-gray-500" />
            <input
              type="text"
              value={inputValue}
              onChange={(e) => setInputValue(e.target.value.toLowerCase())}
              placeholder="Enter Project name"
              className="placeholder-gray-500 text-sm p-2 outline-none w-full bg-transparent ml-2"
            />
          </div>

          {/* Country List */}
          <div className="max-h-48 overflow-y-auto">
            {data?.map((data) => (
              <li
                key={data}
                className={`p-3 text-sm hover:bg-sky-600 hover:text-white cursor-pointer transition-colors duration-200 ${
                  data?.toLowerCase() === selected?.toLowerCase()
                    ? "bg-sky-600 text-white"
                    : "text-gray-900"
                } ${
                  data?.toLowerCase().startsWith(inputValue)
                    ? "block"
                    : "hidden"
                }`}
                onClick={() => {
                  if (data?.toLowerCase() !== selected.toLowerCase()) {
                    setSelected(data);
                    setOpen(false);
                    setInputValue("");
                    navigator("/table/"+data);    
                  }
                }}
              >
                {data}
              </li>
            ))}
          </div>
        </ul>
      </div>
    </div>
    </div>
  )
}
