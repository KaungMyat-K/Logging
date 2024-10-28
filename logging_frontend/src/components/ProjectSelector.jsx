import React, { useEffect, useState } from 'react'

export default function ProjectSelector() {

  const [countries, setCountries] = useState(null);
  const [inputValue, setInputValue] = useState("");
  const [selected, setSelected] = useState("");
  const [open, setOpen] = useState(false);

  useEffect(() => {
    fetch("https://restcountries.com/v2/all?fields=name")
      .then((res) => res.json())
      .then((data) => {
        setCountries(data);
      });
  }, []);

  return (
    <div className="flex items-center justify-center h-screen bg-gray-100">
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
            {countries?.map((country) => (
              <li
                key={country?.name}
                className={`p-3 text-sm hover:bg-sky-600 hover:text-white cursor-pointer transition-colors duration-200 ${
                  country?.name?.toLowerCase() === selected?.toLowerCase()
                    ? "bg-sky-600 text-white"
                    : "text-gray-900"
                } ${
                  country?.name?.toLowerCase().startsWith(inputValue)
                    ? "block"
                    : "hidden"
                }`}
                onClick={() => {
                  if (country?.name?.toLowerCase() !== selected.toLowerCase()) {
                    setSelected(country?.name);
                    setOpen(false);
                    setInputValue("");
                  }
                }}
              >
                {country?.name}
              </li>
            ))}
          </div>
        </ul>
      </div>
    </div>
  )
}
